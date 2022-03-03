package wan.home.bean

data class BannerData(
    var desc: String? = null,
    var imagePath: String? = null,
    var title: String? = null,
    var url: String? = null
)


class Banner {
    var list: MutableList<BannerData>? = null
}