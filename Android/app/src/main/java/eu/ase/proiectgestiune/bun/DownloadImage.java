package eu.ase.proiectgestiune.bun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView img_apartament;

        public DownloadImage(ImageView img_apartament) {
        this.img_apartament = img_apartament;
        }

        protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap img_ap = null;
        try {
        InputStream in = new java.net.URL(urldisplay).openStream();
                img_ap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
        e.printStackTrace();
        }
        return img_ap;
        }

        protected void onPostExecute(Bitmap result) {
        img_apartament.setImageBitmap(result); }
        }
