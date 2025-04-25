package org.sopt.at.screen

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.data.TvProgram
import org.sopt.at.viewmodel.HomeViewModel


@Composable
fun TopBar() {
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

            )
        }
    }
}


@Composable
fun BannerView(viewModel: HomeViewModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
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
                                color = Color.Black,
                                offset = Offset(4f, 4f),
                                blurRadius = 8f
                            )
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = (-8).dp)

                    )
                }
            }
        }
    }
}






@Composable
fun NowBoarding (programs: List<TvProgram>) {
    Column (
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = "지금 방영 중 콘텐츠",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow (
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

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { TopBar() }
        item { BannerView(viewModel) }
        item { Top20Section(programs = viewModel.top20List) }
        item { NowBoarding(programs =  viewModel.nowList) }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomeScreenPreview() {
    val dummyList = listOf(
        TvProgram(id = 1, title = "신병", imageRes = R.drawable.tving1, rank = 1),
        TvProgram(id = 2, title = "산지직송", imageRes = R.drawable.tving2, rank = 2),
        TvProgram(id = 3, title = "전공의", imageRes = R.drawable.tving3, rank = 3),
        TvProgram(id = 4, title = "이가인지명", imageRes = R.drawable.tving4, rank = 4),
        TvProgram(id = 5, title = "내가죽기일주일전", imageRes = R.drawable.tving5, rank = 5)
    )


    Surface(color = Color.Black) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { TopBar() }
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(dummyList) { program ->
                        Image(
                            painter = painterResource(id = program.imageRes),
                            contentDescription = program.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
            item {
                Top20Section(programs = dummyList)
            }
            item {
                NowBoarding(programs = dummyList)
            }
        }
    }
}
