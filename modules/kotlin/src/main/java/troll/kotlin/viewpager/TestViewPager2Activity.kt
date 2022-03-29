package troll.kotlin.viewpagerimport android.os.Bundleimport androidx.fragment.app.Fragmentimport androidx.viewpager2.adapter.FragmentStateAdapterimport androidx.viewpager2.widget.ViewPager2import com.google.android.material.tabs.TabLayoutimport com.google.android.material.tabs.TabLayoutMediatorimport troll.eth.base.BaseActivityimport troll.kotlin.Rclass TestViewPager2Activity : BaseActivity<Any?>() {    private var layoutTab: TabLayout? = null    private var layoutvp: ViewPager2? = null    private val tabList = listOf("关注", "推荐", "最新")    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)//        setContentView(R.layout.activity_view_pager2)        layoutTab = findViewById(R.id.layout_tab)        layoutvp = findViewById(R.id.layout_vp)        layoutvp?.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT        layoutvp?.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {            override fun getItemCount(): Int = tabList.size            override fun createFragment(position: Int): Fragment {                return TestFragment().apply {                    arguments = Bundle().also {                        it.putString("tag",tabList[position])                    }                }            }        }        layoutTab?.let { tabLayout ->            layoutvp?.let {                TabLayoutMediator(tabLayout, it) { tab, position ->                    tab.text = tabList[position]                }.attach()            }        }    }}