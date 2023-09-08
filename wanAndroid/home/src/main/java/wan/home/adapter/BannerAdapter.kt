package wan.home.adapterimport android.view.LayoutInflaterimport android.view.ViewGroupimport androidx.recyclerview.widget.DiffUtilimport androidx.recyclerview.widget.ListAdapterimport androidx.recyclerview.widget.RecyclerViewimport coil.loadimport com.youth.banner.adapter.BannerImageAdapterimport com.youth.banner.holder.BannerImageHolderimport wan.common.Rimport wan.home.bean.Bannerimport wan.home.bean.BannerDataimport wan.home.databinding.LayoutItemHomeBannerBinding/** * author : TangPeng * date : 3/3/22 09:52 * description : */class HomeBannerAdapter(val onClick: (Banner) -> Unit) :    ListAdapter<Banner, HomeBannerAdapter.HomeViewHolder>(HomeBannerDiffCallback) {    class HomeViewHolder(bd: LayoutItemHomeBannerBinding, val onClick: (Banner) -> Unit) :        RecyclerView.ViewHolder(bd.root) {        private var banner = bd.itemBanner        private var currentBanner: BannerData? = null        init {//            bd.root.onClick { currentBanner?.let { onClick(it) } }        }        fun bind(data: Banner) {//            currentBanner = data            banner.setAdapter(object : BannerImageAdapter<BannerData>(data.list) {                override fun onBindView(                    holder: BannerImageHolder?, data: BannerData?, position: Int, size: Int                ) {                    holder?.imageView?.load(data?.imagePath) {                        placeholder(R.mipmap.com_bg_placeholder)                    }                }            })        }    }    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {        val bd =            LayoutItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)        return HomeViewHolder(bd, onClick)    }    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {        val item = getItem(position)        holder.bind(item)    }}object HomeBannerDiffCallback : DiffUtil.ItemCallback<Banner>() {    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {        return oldItem == newItem    }    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {        return oldItem.list?.size == newItem.list?.size    }}