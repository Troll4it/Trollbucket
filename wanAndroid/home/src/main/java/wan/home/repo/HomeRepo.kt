package wan.home.repoimport wan.common.network.createApiimport wan.home.HomeApi/** * author : TangPeng * date : 2/28/22 17:33 * description : */class HomeRepo {    private val homeApi = createApi<HomeApi>()    /**     * 每日问题     */    suspend fun getDailyQuestion(page: Int) = homeApi.getDailyQuestion(page)    /**     * 首页文章     */    suspend fun getHomeArticle(page: Int) = homeApi.getHomeArticle(page)    /**     * Banner 数据     */    suspend fun getBanner() = homeApi.getBanner()}