package troll.kotlin.reflectimport android.view.LayoutInflaterimport troll.btc.extensions.onClickimport troll.eth.base.viewbinding.BaseActivityimport troll.kotlin.databinding.ActivityReflectBindingimport java.lang.reflect.Method/** * author : TangPeng * date : 5/30/22 11:36 * description :  反射的Activity */class ReflectActivity : BaseActivity<ActivityReflectBinding>() {    override fun getBinding(inflater: LayoutInflater): ActivityReflectBinding =        ActivityReflectBinding.inflate(inflater)    override fun flowData() {        val reflectClazz = ReflectClazz()        reflectClazz.initData()        bd.reflectMb.onClick {            val field = reflectClazz.javaClass.getDeclaredField("dog")            field.isAccessible = true            val type = field.type            type.newInstance()            val drinkMethod: Method = type.getMethod("drink", String::class.java)            drinkMethod.isAccessible = true            drinkMethod.invoke( field  , "奶牛")        }    }    override fun flowView() {    }}