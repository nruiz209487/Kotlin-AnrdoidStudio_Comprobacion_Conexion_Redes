package com.example.comprobacionformasdecominicacion

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comprobacionformasdecominicacion.ui.theme.ComprobacionFormasDeCominicacionTheme
/**
 * Main que contiene la vista mainpage
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComprobacionFormasDeCominicacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPage(
                        modifier = Modifier.padding(innerPadding),
                        context = this
                    )
                }
            }
        }
    }
}

/**
 * pagina principal contiene una caja con u row de cajas que cambian e color dependiendo de el res de lasfunciones
 */
@Composable
fun MainPage(modifier: Modifier = Modifier, context  :Context) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "WIFI: ")
                Box(
                    modifier = Modifier
                        .background(if (wifiActive(context)) Color.Red else Color.Green, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                    ,contentAlignment = Alignment.Center
                ) {
                    Text(text = "Hola, Jetpack Compose!", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Bluetooth: ")
                Box(
                    modifier = Modifier
                        .background(if (bluetoothActive(context)) Color.Red else Color.Green, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                    ,contentAlignment = Alignment.Center
                ) {
                    Text(text = "STATE", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "NFC: ")
                Box(

                    modifier = Modifier
                        .background(if (nfcActive(context)) Color.Red else Color.Green, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                    ,contentAlignment = Alignment.Center
                ) {
                    Text(text = "STATE", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "DATA MOBILE: ")
                Box(

                    modifier = Modifier
                        .background(if (gpsActive(context)) Color.Red else Color.Green, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                    ,contentAlignment = Alignment.Center
                ) {
                    Text(text = "STATE", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "MOBILE DATA: ")
                Box(

                    modifier = Modifier
                        .background(if (mobileActive(context)) Color.Red else Color.Green, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
                    ,contentAlignment = Alignment.Center
                ) {
                    Text(text = "STATE", color = Color.White)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "GPs: ")
                Box(

                    modifier = Modifier
                        .background(if (gpsActive(context)) Color.Red else Color.Green, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                    ,contentAlignment = Alignment.Center
                ) {
                    Text(text = "STATE", color = Color.White)
                }
            }
        }
    }



}

/**
 * funcion de tipo booleano que comprueba si el wifi esta activo
 */
fun wifiActive(context: Context): Boolean {
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    return wifiManager.isWifiEnabled
}
/**
 * funcion de tipo booleano que comprueba si el bluetooth esta activo
 */
fun bluetoothActive(context: Context): Boolean {
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
    return bluetoothAdapter != null && bluetoothAdapter.isEnabled
}
/**
 * funcion de tipo booleano que comprueba si el nfc esta activo
 */
fun nfcActive(context: Context): Boolean {
    val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(context)
    return nfcAdapter != null && nfcAdapter.isEnabled
}
/**
 * funcion de tipo booleano que comprueba si los datos esta activo
 */
fun mobileActive(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}
/**
 * funcion de tipo booleano que comprueba si el gpsActive esta activo
 */
fun gpsActive(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

