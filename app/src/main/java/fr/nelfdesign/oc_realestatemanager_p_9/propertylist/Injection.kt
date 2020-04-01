package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
class Injection {

    companion object{

        private fun provideExecutor() : Executor{
            return Executors.newSingleThreadExecutor()
        }

        fun provideViewModelFactory() : ViewModelFactory{
            val executor = provideExecutor()
            return ViewModelFactory(executor)
        }

    }
}