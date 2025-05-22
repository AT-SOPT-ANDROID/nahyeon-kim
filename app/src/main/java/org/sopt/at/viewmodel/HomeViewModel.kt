package org.sopt.at.viewmodel

import androidx.lifecycle.ViewModel
import org.sopt.at.R
import org.sopt.at.data.entity.TvProgram

class HomeViewModel : ViewModel() {

    private val _bannerList = listOf(
        TvProgram(1, "신병3", R.drawable.tving1, rank = 1),
        TvProgram(2, "언니네산지직송", R.drawable.tving2, rank = 2),
        TvProgram(3, "전공의생활", R.drawable.tving3, rank = 3),
        TvProgram(4, "이가인지명", R.drawable.tving4, rank = 4),
        TvProgram(5, "내가죽기일주일전", R.drawable.tving5, rank = 5),
        TvProgram(6, "지구오락실", R.drawable.tving6, rank = 6)
    )

    val bannerList: List<TvProgram> = _bannerList

    val top20List: List<TvProgram> = List(20) { index ->
        val program = _bannerList[index % _bannerList.size]
        program.copy(rank = index + 1)
    }

    val nowList: List<TvProgram> = _bannerList

    val entertainmentList: List<TvProgram> = _bannerList.shuffled()
    val movieList: List<TvProgram> = _bannerList.shuffled()
    val sportsList: List<TvProgram> = _bannerList.shuffled()
    val animeList: List<TvProgram> = _bannerList.shuffled()
    val newsList: List<TvProgram> = _bannerList.shuffled()
}
