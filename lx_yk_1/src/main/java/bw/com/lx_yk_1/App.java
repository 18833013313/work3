package bw.com.lx_yk_1;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions build1 = new DisplayImageOptions
                .Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))
                .build();

        ImageLoaderConfiguration build = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCacheSizePercentage(20)
                .diskCache(new UnlimitedDiskCache(new File(getCacheDir(),"images")))
                .defaultDisplayImageOptions(build1)
                .build();

        ImageLoader.getInstance().init(build);
    }
}
