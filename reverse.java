

//for dc
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;



public class reverse {
    public static byte[] initVector = {-34, -83, -66, -17, -34, -83, -66, -17, -34, -83, -66, -17, -34, -83, -66, -17};
    public static String packageName = "com.mobisec.dexclassloader";
    public static String parentDir = "E:\\nhidayat\\mobile_security\\apk\\loadme";
    public static String path = "/data/user/0/com.example.myapp/code_cache/test.dex";
    public String TAG = "MAINAPP";
    public String flag = "MOBISEC{hard_enough_ugh}";


    public static void main(String[] args){
        String str_gu = gu();
        String str_gf = gf();
        String str_gc = gc();
        String str_gm = gm();

        System.out.println("str_gu: " + str_gu);    //str_gu: https://challs.reyammer.io/loadme/stage1.apk
        System.out.println("str_gf: " + str_gf);    //str_gf: test.dex
        System.out.println("str_gc: " + str_gc);    //str_gc: com.mobisec.stage1.LoadImage
        System.out.println("str_gm: " + str_gm);    //str_gm: load

        String path = dc(gu());

        System.out.println("path: " + path);
    }

    public static String gu() {
        String url = ds("MAi9CEe4K9a+JzgsNqdYYh13dk7SQQ/yo5BN5HF39nYtgnOBmO4EV9Y2sQDthTG9");
        return url;
    }

    public static String gf(){
        return ds("QLrdlqkhOkxIe5FEfpCLWw==");
    }

    public static String gc(){
        return ds("ca9O9YbCZ/+vIYUvxPQUHA4SgyL/m3cwlvVZ4ArkBFQ=");
    }

    public static String gm(){
        return ds("6RSjLOfRkvb/qXa34Y7cOQ==");
    }



    public boolean lc(String path){
        File tmpDir = new File(this.context.getFilesDir().getAbsolutePath());
        File file = new File(path);
        DexClassLoader classloader = new DexClassLoader(file.getAbsolutePath(), tmpDir.getAbsolutePath(), null, classLoader.getSystemClassLoader());
        file.delete();
        File[] ftemp = tmpDir.listFiles();
        for (File f: ftemp) {
            f.delete();
        }
        try{
            Class<?> classToLoad = classloader.loadClass(gc());
            Method method = classToLoad.getDeclaredMethod(gm(), Context.class, String.class);
            boolean res = ((Boolean) method.invoke(classToLoad, this.context, this.flag)).booleanValue();
            return res;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**


     */
    // public boolean start(String flag){
    //     // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    //     // StrictMode.setThreadPolicy(policy);
    //     // setContext(ctx);
    //     // setUserInput(flag);
    //     // String path = dc(gu()); // path --> 
    //     da(path);
    //     return lc(path);
    // }

    public static String dc(String url){
        try{
            System.out.println(url);
            URL downloaded_url = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) downloaded_url.openConnection();
            urlConnection.connect();
            // File file = new File(this.context.getCodeChaceDir(), gf()); //what is this
            File file = new File(parentDir, gf());
            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            while (true){
                int bufferLength = inputStream.read(buffer);
                if (bufferLength <= 0){
                    fileOutput.close();
                    return file.getAbsolutePath();
                }
                fileOutput.write(buffer, 0, bufferLength);
            }
        } catch (Exception e){
            return null;
        }
    }

    public void da(String path){
        byte[] xorKey = packageName.getBytes();
        File file = new File(path);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        byte[] decbytes= new byte[size];
        try{
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, byte.length);
            buf.close();
            for (int i = 0; i < size; i++){
                decbytes[i] = (byte) (bytes[i] ^ xorKey[i % xorKey.length]);
            }
            File outFile = new File(path);
            FileOutputStream out = new FileOutputStream(outFile, false);
            out.write(decbytes);
            out.flush();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String ds(String enc){
        try{
            String[] parts = packageName.split(Pattern.quote("."));
            String key = parts[1] + parts[0] + "key!!!";
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(enc));
            // byte[] original = cipher.doFinal(Base64.getDecoder().decode(enc.getBytes()));
            return new String(original);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}