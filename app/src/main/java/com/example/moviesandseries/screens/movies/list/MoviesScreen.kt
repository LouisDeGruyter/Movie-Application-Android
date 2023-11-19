package com.example.moviesandseries.screens.movies.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.moviesandseries.model.movie.MovieIndex
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
private const val COLUMN_COUNT = 2
private val GRID_SPACING= 8.dp
@Composable
fun MoviesScreen(movieViewModel: MovieViewModel, onMovieClick: (movieId:Int) -> Unit) {
    var moviesByPage = movieViewModel.moviePager.collectAsLazyPagingItems()
    when(moviesByPage.loadState.refresh){
        is LoadState.Loading -> Text(text = "Loading")
        is LoadState.Error -> Text(text = "Error")
        else -> {
            displayMovies(movies = moviesByPage, onMovieClick = onMovieClick )

        }
    }
   
}



@Composable
fun displayMovies(movies: LazyPagingItems<MovieIndex>, onMovieClick: (movieId: Int) -> Unit) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            top = GRID_SPACING,
            bottom = GRID_SPACING
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING,Alignment.CenterHorizontally),
        content = {
            items(movies.itemCount){ index ->
                val movie = movies.peek(index)?: return@items
                MovieItem(movie = movie, onMovieClick = onMovieClick,
                    Modifier
                        .height(320.dp)
                        .padding(vertical = GRID_SPACING) )
                Text(text = index.toString())
            }

        }
    )

}
@Composable
fun displayMovies2(movies: LazyPagingItems<MovieIndex>, onMovieClick: (movieId: Int) -> Unit) {
    LazyColumn {
        itemsIndexed(items = movies) { index, movie ->
            movie?.let {
                MovieItem(movie = it, onMovieClick = onMovieClick, height = Modifier.height(320.dp))
                Text(text = index.toString())
            }
        }
    }
}



@Composable
fun MovieItem(movie: MovieIndex, onMovieClick: (Int) -> Unit, height: Modifier) {
    // Customize this composable based on how you want to display each movie item
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { onMovieClick(movie.id) })
    ) {
        // You can use Image, CoilImage, or any other composable to display the movie poster
        Text(text = movie.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        // Add other movie details as needed
    }
}

