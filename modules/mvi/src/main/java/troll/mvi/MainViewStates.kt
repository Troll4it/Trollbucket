package troll.mvisealed class MviViewAction {    object ButtonClick : MviViewAction()}sealed class MviViewEvent {    data class ShowTitle(val title: String) : MviViewEvent()}