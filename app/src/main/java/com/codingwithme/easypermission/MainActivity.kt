package com.codingwithme.easypermission

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks,EasyPermissions.RationaleCallbacks {
    private val CAMERA_PERMISSION = 100
    private val LOCATION_PERMISSION = 200
    private val SMS_PERMISSION = 300
    private val CONTACTS_PERMISSION = 400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnCamera = findViewById<Button>(R.id.btn_camera)
        var btnLocation = findViewById<Button>(R.id.btn_location)
        var btnSms = findViewById<Button>(R.id.btn_sms)
        var btnContact = findViewById<Button>(R.id.btn_contact)


        btnCamera.setOnClickListener {

            askCameraPermission()
        }

        btnLocation.setOnClickListener {

            askLocationPermission()
        }

        btnSms.setOnClickListener {

            askSmsPermission()
        }

        btnContact.setOnClickListener {

            askContactsPermission()
        }
    }


    //camera permission
    private fun askCameraPermission(){
        if (hasCameraPermission()){
            Toast.makeText(this,"Camera Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            EasyPermissions.requestPermissions(
                this,
            "This app needs access to your camera so you can take pictures.",
            CAMERA_PERMISSION,
            Manifest.permission.CAMERA)
        }
    }
    private fun hasCameraPermission():Boolean{
        return EasyPermissions.hasPermissions(this,Manifest.permission.CAMERA)
    }

    //location permission
    private fun askLocationPermission(){
        if (hasLocationPermission()){
            Toast.makeText(this,"Location Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your Location to know where you are.",
                LOCATION_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    private fun hasLocationPermission():Boolean{
        return EasyPermissions.hasPermissions(this,Manifest.permission.ACCESS_FINE_LOCATION)
    }

    //sms permission
    private fun askSmsPermission(){
        if (hasSmsPermission()){
            Toast.makeText(this,"Sms Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your sms to read all your great messages.",
                SMS_PERMISSION,
                Manifest.permission.READ_SMS)
        }
    }
    private fun hasSmsPermission():Boolean{
        return EasyPermissions.hasPermissions(this,Manifest.permission.READ_SMS)
    }

    //contact permission
    private fun askContactsPermission(){

        if (hasContactsPermission()){
            Toast.makeText(this,"Contacts Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your contact to read all your contacts.",
                CONTACTS_PERMISSION,
                Manifest.permission.READ_CONTACTS)
        }
    }
    private fun hasContactsPermission():Boolean{
        return EasyPermissions.hasPermissions(this,Manifest.permission.READ_CONTACTS)
    }



    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d("TAG", "onPermissionsGranted:" + requestCode + ":" + perms.size)

    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d("TAG", "onRationaleDemnied:" + requestCode)
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d("TAG", "onRationaleAccepted:" + requestCode)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }
}