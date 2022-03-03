package wan.home.adapterimport android.view.LayoutInflaterimport android.view.ViewGroupimport android.widget.TextViewimport androidx.recyclerview.widget.DiffUtilimport androidx.recyclerview.widget.ListAdapterimport androidx.recyclerview.widget.RecyclerViewimport troll.btc.extensions.onClickimport wan.home.bean.ArticleDataimport wan.home.databinding.LayoutItemHomeArticleBinding/** * author : TangPeng * date : 3/2/22 21:43 * description : */class HomeArticleAdapter(private val onClick: (ArticleData) -> Unit) :    ListAdapter<ArticleData, HomeArticleAdapter.ArticleViewHolder>(ArticleDiffCallback) {    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {        val bd =            LayoutItemHomeArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)        return ArticleViewHolder(bd, onClick)    }    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {        val item = getItem(position)        holder.bind(item)    }    class ArticleViewHolder(        bd: LayoutItemHomeArticleBinding,        val onClick: (ArticleData) -> Unit    ) : RecyclerView.ViewHolder(bd.root) {        private var currentArticle: ArticleData? = null        private val tvTitle: TextView = bd.tvArticleTitle        private val tvAuthor: TextView = bd.tvArticleAuthor        private val tvTime: TextView = bd.tvHomeInfoTime        init {            bd.root.onClick { currentArticle?.let { onClick(it) } }        }        fun bind(articleData: ArticleData) {            currentArticle = articleData            tvAuthor.text = articleData.author            tvTime.text = articleData.publishTime.toString()            tvTitle.text = articleData.title        }    }}object ArticleDiffCallback : DiffUtil.ItemCallback<ArticleData>() {    override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {        return oldItem == newItem    }    override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {        return oldItem.courseId == newItem.courseId    }}