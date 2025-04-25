package org.sopt.at.viewmodel

import androidx.lifecycle.ViewModel
import org.sopt.at.R
import org.sopt.at.data.TvProgram

class HomeViewModel : ViewModel() {

    private val _bannerList = listOf(
        TvProgram(1, "바니와 오빠들", R.drawable.tving1, rank = 1),
        TvProgram(2, "신병3", R.drawable.tving2, rank = 2),
        TvProgram(3, "패밀리", R.drawable.tving3, rank = 3),
        TvProgram(4, "하트시그널", R.drawable.tving4, rank = 4),
        TvProgram(5, "환승연애", R.drawable.tving5, rank = 5),
        TvProgram(6, "소년판타지", R.drawable.tving6, rank = 6)
    )

    val bannerList: List<TvProgram> = _bannerList

    val top20List: List<TvProgram> = List(20) { _bannerList[it % _bannerList.size] }

    val nowList : List<TvProgram> = _bannerList
}
