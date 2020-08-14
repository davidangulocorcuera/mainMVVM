package david.angulo.productsApp.modules.driverSelected

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import david.angulo.productsApp.application.App
import david.angulo.productsApp.model.Driver
import david.angulo.productsApp.modules.base.BaseViewModel
import david.angulo.productsApp.modules.utils.ConstantsPlatform

class DriverSelectViewModel (var app: Application) : BaseViewModel(app){
    init {
        (app as? App)?.component?.inject(this)
    }
    var driver = MutableLiveData<Driver>()

    fun getArguments(args: Bundle){
        driver.postValue(args.getSerializable(ConstantsPlatform.DRIVER) as Driver)
    }
}