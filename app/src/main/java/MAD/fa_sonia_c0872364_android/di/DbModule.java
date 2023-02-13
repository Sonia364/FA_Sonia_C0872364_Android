package MAD.fa_sonia_c0872364_android.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;
import MAD.fa_sonia_c0872364_android.db.AppDatabase;
import MAD.fa_sonia_c0872364_android.db.DbDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module()
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "ProductDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbDao provideDbDao(AppDatabase appDatabase) {
        return appDatabase.dbDao();
    }

}
