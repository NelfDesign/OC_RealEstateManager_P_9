package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import android.content.Context
import fr.nelfdesign.oc_realestatemanager_p_9.app.App.Companion.db
import fr.nelfdesign.oc_realestatemanager_p_9.database.repository.PropertyDaoRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
class Injection {

    companion object{

        private fun providePropertyDataSource() : PropertyDaoRepository{
            return PropertyDaoRepository(db.PropertyDao())
        }

        private fun provideExecutor() : Executor{
            return Executors.newSingleThreadExecutor()
        }

        fun provideViewModelFactory() : ViewModelFactory{
           // val propertyDataSource = providePropertyDataSource()
            val executor = provideExecutor()
            return ViewModelFactory(executor)
        }

    }
}