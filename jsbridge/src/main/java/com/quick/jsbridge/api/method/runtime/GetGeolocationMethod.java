package com.quick.jsbridge.api.method.runtime;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.webkit.WebView;

import com.quick.core.net.HttpUtil;
import com.quick.jsbridge.util.app.AppUtil;
import com.quick.jsbridge.util.common.RuntimeUtil;
import com.quick.jsbridge.util.gis.CoordMath;
import com.quick.jsbridge.util.gis.MyLatLngPoint;
import com.quick.jsbridge.bridge.Callback;
import com.quick.jsbridge.bridge.IApiMethod;
import com.quick.jsbridge.view.IQuickFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quick.com.jsbridge.R;

/**
 * 获取经纬度以及详细地址信息
 * <p>
 * 参数：
 * isShowDetail：是否显示详细地址信息 1：显示，0：不显示，默认为0
 * coordinate:返回的坐标系类型 1：GCJ02（火星坐标系/高德） 0：WGS84（地球坐标系）
 * 返回：
 * location：经纬度
 * addressComponent：详细地址信息
 */
public class GetGeolocationMethod implements IApiMethod {

    @Override
    public String name() {
        return "getGeolocation";
    }

    @Override
    public void execute(final IQuickFragment webLoader, final WebView wv, final JSONObject param,final Callback callback) {
        final boolean isShowDetail = "1".equals(param.optString("isShowDetail", "0"));
        final int coordinate = param.optInt("coordinate", 1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocationManager lm = (LocationManager) webLoader.getPageControl().getActivity().getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
                criteria.setAltitudeRequired(false);//不要求海拔
                criteria.setBearingRequired(false);//不要求方位
                criteria.setCostAllowed(true);//允许有花费
                criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

                //从可用的位置提供器中，匹配以上标准的最佳提供器
                String provider = lm.getBestProvider(criteria, true);
                if (TextUtils.isEmpty(provider)) {
                    callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.toast_gps_not_open));
                    return;
                }
                if (ActivityCompat.checkSelfPermission(AppUtil.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AppUtil.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.toast_no_permission));
                    return;
                }
                Location location = lm.getLastKnownLocation(provider);
                if (location == null) {
                    callback.applyFail(webLoader.getPageControl().getContext().getString(R.string.toast_location_fail));
                    return;
                }
                MyLatLngPoint myLatLngPoint = new MyLatLngPoint(location.getLatitude(), location.getLongitude());
                if (coordinate == 1) {
                    myLatLngPoint = CoordMath.wgs2gcj(myLatLngPoint);
                }
                try {
                    String latlng = myLatLngPoint.getLat() + "," + myLatLngPoint.getLng();
                    Map<String, Object> jsonObject = new HashMap<>();
                    jsonObject.put("longitude", myLatLngPoint.getLng());
                    jsonObject.put("latitude", myLatLngPoint.getLat());

                    if (isShowDetail) {
                        //百度地图服务
                        String bs = HttpUtil.get("http://api.map.baidu.com/geocoder/v2/?location=" + latlng + "&output=json&pois=1&ak=" + webLoader.getPageControl().getContext().getString(R.string.baiduMap_ak));
                        if (bs != null) {
                            JSONObject address = new JSONObject(bs);
                            //获取results节点下的位置信息
                            int status = address.getInt("status");
                            if (status == 0) {
                                address = address.getJSONObject("result");
                                //解析成功"addressComponent":{"country":"中国","country_code":0,"province":"江苏省","city":"苏州市","district":"","adcode":"","street":"","street_number":"","direction":"","distance":""}
                                address = address.getJSONObject("addressComponent");
                                jsonObject.put("addressComponent", address);
                            }
                        }
                    }
                    callback.applySuccess(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.applyFail(e.getMessage());
                }
            }
        }).start();
    }
}
