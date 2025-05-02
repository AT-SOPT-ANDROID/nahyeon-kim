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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.data.TvProgram
import org.sopt.at.viewmodel.AuthViewModel
import org.sopt.at.viewmodel.HomeViewModel


@Composable
fun TopBar(navController: NavHostController, authViewModel: AuthViewModel) {
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
                tint = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile Icon",
                tint = Color.White,
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
        items(viewModel.bannerList) { program ->
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
fun Top20Section(programs: List<TvProgram>) {
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = "오늘의 티빙 TOP 20",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(programs) { program ->
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
                        color = Color.White,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.displayLarge.copy(
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(4f, 4f), blurRadius = 8f
                            )
                        ),
                        modifier = Modifier.align(Alignment.BottomStart)

                    )
                }
            }
        }
    }
}


@Composable
fun NowBoarding(programs: List<TvProgram>) {
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = "지금 방영 중 콘텐츠",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(programs) { program ->
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
    viewModel: HomeViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    val tabTitles = listOf("드라마", "예능", "영화", "스포츠", "애니", "뉴스")
    var selectedTabIndex by remember { mutableIntStateOf(0) }



    Scaffold(
        topBar = { TopBar(navController, authViewModel) },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            stickyHeader {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    indicator = {},
                    divider = {}
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Gray,
                            interactionSource = NoRippleInteractionSource,
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 12.sp,
                                    color = if (selectedTabIndex == index) Color.White else Color.Gray
                                )
                            }
                        )
                    }
                }
            }

            item { BannerView(viewModel) }

            when (selectedTabIndex) {
                0 -> { // 드라마
                    item { Top20Section(programs = viewModel.top20List) }
                    item { NowBoarding(programs = viewModel.nowList) }
                }
                1 -> { // 예능
                    items(viewModel.entertainmentList) { program ->
                        ProgramItem(program)
                    }
                }
                2 -> { // 영화
                    items(viewModel.movieList) { program ->
                        ProgramItem(program)
                    }
                }
                3 -> { // 스포츠
                    items(viewModel.sportsList) { program ->
                        ProgramItem(program)
                    }
                }
                4 -> { // 애니
                    items(viewModel.animeList) { program ->
                        ProgramItem(program)
                    }
                }
                5 -> { // 뉴스
                    items(viewModel.newsList) { program ->
                        ProgramItem(program)
                    }
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
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


