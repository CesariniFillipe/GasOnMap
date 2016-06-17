package com.toninho.gasonmap.Json;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.toninho.gasonmap.Posto;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConsumirJsonActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DownloadJsonAsyncTask()
                .execute("http://10.0.5.180:8080/samples/mock_pessoas");
    }

    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Posto posto = (Posto) l.getAdapter().getItem(position);

        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(posto.getNome()));
        startActivity(it);
    }

    class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Posto>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ConsumirJsonActivity.this, "Aguarde",
                    "Baixando JSON, Por Favor Aguarde...");
        }

        @Override
        protected List<Posto> doInBackground(String... params) {
            String urlString = params[0];

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlString);

            try {
                HttpResponse response = httpclient.execute(httpget);

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    InputStream instream = entity.getContent();

                    String json = toString(instream);
                    instream.close();

                    List<Posto> postos = getPostos(json);

                    return postos;
                }
            } catch (Exception e) {
                Log.e("ERRO", "Falha ao acessar Web service", e);
            }
            return null;
        }

        private List<Posto> getPostos(String jsonString) {

            List<Posto> postos = new ArrayList<>();

            try {
                JSONArray postoLists = new JSONArray(jsonString);
                JSONObject trendList = postoLists.getJSONObject(0);
                JSONArray postosArray = trendList.getJSONArray("postos");

                JSONObject posto;

                for (int i = 0; i < postosArray.length(); i++) {
                    posto = new JSONObject(postosArray.getString(i));

                    Log.i("DEVMEDIA", "nome=" + posto.getString("nome"));

                    Posto objetoPosto = new Posto();

                    objetoPosto.setNome(posto.getString("nome"));
                    objetoPosto.setLat(posto.getString("lat"));
                    objetoPosto.setLng(posto.getString("long"));
                    objetoPosto.setGas(posto.getString("gas"));
                    objetoPosto.setAlc(posto.getString("alc"));
                    objetoPosto.setDie(posto.getString("die"));

                    postos.add(objetoPosto);
                }
            } catch (JSONException e) {
                Log.e("DEVMEDIA", "Erro no parsing do JSON", e);
            }

            return postos;
        }

        @Override
        protected void onPostExecute(List<Posto> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.size() > 0) {
                ArrayAdapter<Posto> adapter = new ArrayAdapter<>(
                        ConsumirJsonActivity.this,
                        android.R.layout.simple_list_item_1, result);
                setListAdapter(adapter);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ConsumirJsonActivity.this).setTitle("Atenção")
                        .setMessage("Não foi possivel acessar essas informções...")
                        .setPositiveButton("OK", null);
                builder.create().show();
            }
        }

        private String toString(InputStream is) throws IOException {

            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int lidos;
            while ((lidos = is.read(bytes)) > 0) {
                baos.write(bytes, 0, lidos);
            }
            return new String(baos.toByteArray());
        }
    }

}