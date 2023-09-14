package com.example.tp06_contentprovider;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Telephony;
import android.util.Log;
import android.widget.TextView;

public class MensajeService extends Service {
    private Thread hilo;
    private boolean flag=true;
    private int contador=0;
    public MensajeService() { }

    private Handler handler;
    private StringBuilder mensajeBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        mensajeBuilder = new StringBuilder();
        handler = new Handler(Looper.getMainLooper());
        acceder();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    public void acceder() {

        Uri llamadas = Uri.parse("content://sms/inbox");
        ContentResolver cr = this.getContentResolver();

        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (flag) {
                        Cursor cursor = cr.query(llamadas, null, null, null, "date DESC LIMIT 5");
                        String fechaMensaje = null;
                        String textoMensaje = null;
                        String contactoMensaje = null;

                        StringBuilder resultado = new StringBuilder();
                        if (cursor.getCount() > 0) {

                            while (cursor.moveToNext()) {

                                int fecha = cursor.getColumnIndex(Telephony.Sms.DATE);
                                int contacto = cursor.getColumnIndex(Telephony.Sms.ADDRESS);
                                int mensaje = cursor.getColumnIndex(Telephony.Sms.BODY);

                                fechaMensaje = cursor.getString(fecha);
                                textoMensaje = cursor.getString(mensaje);
                                contactoMensaje = cursor.getString(contacto);
                                resultado.append("fecha" + fechaMensaje + " contacto" + contactoMensaje + " mensaje " + textoMensaje + "\n");
                                //mostrarMensajeEnPantalla("fecha" + fechaMensaje + " contacto" + contactoMensaje + " mensaje " + textoMensaje);
                            }
                        }
                        Log.d("salida", resultado.toString());

                        Log.d("Contador", "numero : " + contador);
                        contador++;
                        Thread.sleep(9000);
                    }

                } catch (Exception ex) {
                        }
            }
        });
        hilo.start();
    }

   /* private void mostrarMensajeEnPantalla(String mensaje) {
        handler.post(() -> {
            mensajeBuilder.append(mensaje).append("\n");
            TextView textView = findViewById(R.id.tvMensajes);
            textView.setText(mensajeBuilder.toString());

            Log.d("salida", mensaje);
        });
    }*/
}