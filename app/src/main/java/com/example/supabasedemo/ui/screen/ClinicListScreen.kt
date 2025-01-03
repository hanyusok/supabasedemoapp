package com.example.supabasedemo.ui.screen

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.supabasedemo.viewmodel.ClinicListViewModel
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.util.reflect.instanceOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ClinicListViewModel,
) {
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Clinic List",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
            )
        },
        floatingActionButton = {
            AddClinicButton() {}
        }) { padding ->
        val clinicList = viewModel.clinicList.collectAsState(initial = listOf()).value
        if (!clinicList.isNullOrEmpty()) @androidx.compose.runtime.Composable {
            LazyColumn(
                modifier = modifier.padding(padding),
                contentPadding = PaddingValues(5.dp)
            ) {
                itemsIndexed(
                    items = clinicList,
                    key = { _, clinic -> clinic.clinicName }) { _, item ->
                    val state = rememberSwipeToDismissBoxState(
                        confirmValueChange = {
                            viewModel.removeItem(item)
                            true
                        }
                    )
                }


            }
        } else {
            Text(text = "No clinics found")
        }

    }
}
 @Composable
 fun AddClinicButton(
        modifier: Modifier = Modifier, onClick: () -> Unit
 ) {
   FloatingActionButton(
       modifier = modifier,
       onClick = onClick
   ) {
       Icon(
           imageVector = Icons.Default.Add,
           contentDescription = "Add Clinic",
           tint = Color.White
       )
   }

}








