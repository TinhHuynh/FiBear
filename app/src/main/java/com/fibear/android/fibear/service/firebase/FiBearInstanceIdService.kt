package com.fibear.android.fibear.service.firebase

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


/**
 * Created by TINH HUYNH on 3/24/2018.
 */
class FiBearInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("FirebaseIDService", "Refreshed token: " + refreshedToken!!)
        //RefreshToken này nó sẽ chạy đầu tiên khi mày mở app lên
        // => nếu user chưa login thì mày lưu cái refreshedToken này xuống SharedPreference với key là gì đó
        // => Sau khi user login success , mày gọi call API này lên
        //post : http://35.229.53.76/v1/auth/refreshToken
        //{"userId":1,"token":"fW_aHIS8YDk:APA91bEfJnb2QY0DGo8MlnfoiDMMtX6L0oC0Q3lEN6jX8avJTPoZ1yQWXn2HVNcPt6byWMFEHOdyqGYPvnChlrllFs_tT8fHPq2KA9OwILRcLJv64AAiKrIy4rGg4pK0WtFyZs67U_w_"}
        // userId là user nó login vào , token chính là token mày lưu vào sharedpreferenced trước đó
        // Thông báo bên messaging tao làm sẵn hết rồi , giờ chỉ cần gửi token lên thôi nha !
        sendRegistrationToServer(refreshedToken)


    }

    private fun sendRegistrationToServer(refreshedToken: String?) {
        Log.v("Sending to server!!", "Sending==" + refreshedToken!!)
    }

}