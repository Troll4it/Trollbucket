package troll.kotlin.operatorimport troll.eth.base.viewbinding.getViewModelClassdata class People(val name: String, val age: Int) {    fun getMax(): Int {        return age * 10 + name.length    }}class TestPeople {    private val p1 = People("Troll", 20)    private val p2 = People("Troll", 30)    private val list = mutableListOf(p1, p2)    fun test() {        val funMax = People::getMax        funMax.invoke(p1)    }}fun getMaxSort(people: People): Int {    return 0}