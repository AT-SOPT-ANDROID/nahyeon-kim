package org.sopt.at.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.sopt.at.R
import org.sopt.at.data.TvProgram
import org.sopt.at.ui.theme.TivingTheme
import org.sopt.at.viewmodel.AuthViewModel
import org.sopt.at.viewmodel.HomeViewModel

@Composable
fun TopBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.tving_logo),
            contentDescription = "Tving Logo",
            modifier = Modifier.size(80.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_tv),
                contentDescription = "TV Icon",
                tint = TivingTheme.colors.basicWhite,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile Icon",
                tint = TivingTheme.colors.basicWhite,
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        navController.navigate("my")
                    })
        }
    }
}

@Composable
fun BannerView(viewModel: HomeViewModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(480.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(viewModel.bannerList, key = { it.id }) { program ->
            Image(
                painter = painterResource(id = program.imageRes),
                contentDescription = program.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .fillParentMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun Top20Section(
    programs: List<TvProgram>, content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = "오늘의 티빙 TOP 20",
            color = TivingTheme.colors.basicWhite,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = TivingTheme.typography.title.fontFamily
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(programs, key = { it.id }) { program ->
                Box(
                    modifier = Modifier
                        .width(170.dp)
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = program.imageRes),
                        contentDescription = program.title,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(140.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "${program.rank}",
                        color = TivingTheme.colors.basicWhite,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontFamily = TivingTheme.typography.title.fontFamily, shadow = Shadow(
                                color = TivingTheme.colors.basicBlack,
                                offset = Offset(4f, 4f),
                                blurRadius = 8f
                            )
                        ),
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }
            }
        }

        content()
    }
}

@Composable
fun NowBoarding(programs: List<TvProgram>) {
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = "지금 방영 중 콘텐츠",
            color = TivingTheme.colors.basicWhite,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = TivingTheme.typography.title.fontFamily
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(programs, key = { it.id }) { program ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(140.dp)
                ) {
                    Image(
                        painter = painterResource(id = program.imageRes),
                        contentDescription = program.title,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel(),
    viewModel: HomeViewModel = viewModel()
) {
    val tabTitles = listOf("드라마", "예능", "영화", "스포츠", "애니", "뉴스")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { TopBar(navController) }

        stickyHeader {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = TivingTheme.colors.basicBlack,
                contentColor = TivingTheme.colors.basicWhite,
                indicator = {},
                divider = {}) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        selectedContentColor = TivingTheme.colors.basicWhite,
                        unselectedContentColor = TivingTheme.colors.gray02,
                        text = {
                            Text(
                                text = title,
                                fontSize = 10.sp,
                                color = if (selectedTabIndex == index) TivingTheme.colors.basicWhite else TivingTheme.colors.gray02,
                                fontFamily = TivingTheme.typography.title.fontFamily
                            )
                        })
                }
            }
        }

        item { BannerView(viewModel) }

        when (selectedTabIndex) {
            0 -> {
                item {
                    Top20Section(programs = viewModel.top20List) {
                        NowBoarding(programs = viewModel.nowList)
                    }
                }
            }

            1 -> {
                items(viewModel.entertainmentList, key = { it.id }) { program ->
                    ProgramItem(program)
                }
            }

            2 -> {
                items(viewModel.movieList, key = { it.id }) { program ->
                    ProgramItem(program)
                }
            }

            3 -> {
                items(viewModel.sportsList, key = { it.id }) { program ->
                    ProgramItem(program)
                }
            }

            4 -> {
                items(viewModel.animeList, key = { it.id }) { program ->
                    ProgramItem(program)
                }
            }

            5 -> {
                items(viewModel.newsList, key = { it.id }) { program ->
                    ProgramItem(program)
                }
            }
        }
    }
}

@Composable
fun ProgramItem(program: TvProgram) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = program.imageRes),
            contentDescription = program.title,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = program.title,
            color = TivingTheme.colors.basicWhite,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = TivingTheme.typography.body.fontFamily
            )
        )
    }
}
