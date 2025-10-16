package com.finder.roomrelation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.finder.roomrelation.entity.Customers
import com.finder.roomrelation.entity.Exam
import com.finder.roomrelation.entity.Orders
import com.finder.roomrelation.entity.UserDetailsEntity
import com.finder.roomrelation.entity.UserEntity
import com.finder.roomrelation.entity.UserWithDetailModel
import com.finder.roomrelation.ui.theme.RoomRelationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: UserViewModel by viewModels()

    val user = listOf(
        UserEntity(1, name = "user1", email = "email1"),
        UserEntity(2, name = "user2", email = "email2"),
        UserEntity(3, name = "user3", email = "email3"),
        UserEntity(4, name = "user4", email = "email4"),
        UserEntity(5, name = "user5", email = "email5"),
        UserEntity(6, name = "user6", email = "email6")
    )

    val userDetails = listOf(
        UserDetailsEntity(1, "address1", 25, "1234567891", 1),
        UserDetailsEntity(2, "address2", 26, "1234567892", 3),
        UserDetailsEntity(3, "address3", 25, "1234567893", 2),
        UserDetailsEntity(4, "address4", 25, "1234567894", 4),
        UserDetailsEntity(5, "address5", 27, "1234567895", 2),
        UserDetailsEntity(6, "address6", 25, "1234567896", 1)
    )

    val customers = listOf(
        Customers(1, "KNITTED UNION FLAG HOT WATER BOTTLE", "Kitchen", 6, 3.39, 17850),
        Customers(2, "POPPY'S PLAYHOUSE BEDROOM", "Toys", 6, 2.1, 17850),
        Customers(3, "IVORY KNITTED MUG COSY", "Kitchen", 6, 1.65, 13047),
        Customers(4, "BOX OF VINTAGE JIGSAW BLOCKS", "Toys", 3, 4.95, 13047),
        Customers(5, "RED COAT RACK PARIS FASHION", "Clothing", 3, 4.95, 13047),
        Customers(6, "YELLOW COAT RACK PARIS FASHION", "Clothing", 3, 4.95, 13047),
        Customers(7, "BLUE COAT RACK PARIS FASHION", "Clothing", 3, 4.95, 13047)
    )

    val order =
        listOf(
            Orders(orderId = 298463, orderDate = "2021-12-31", paymentDate = "2022-01-01"),
            Orders(orderId = 403820, orderDate = "2022-03-10", paymentDate = "2022-03-10"),
            Orders(orderId = 968832, orderDate = "2022-07-15", paymentDate = "2022-07-15"),
            Orders(orderId = 968885, orderDate = "2022-08-01", paymentDate = "2021-10-12")
        )

    val exam = listOf(
        Exam(name = "Toby Shaw", resultRank = 56, birthdayMonth = "January"),
        Exam(name = "Casey Watson", resultRank = 49, birthdayMonth = "April"),
        Exam(name = "Bennie Lynn", resultRank = 23, birthdayMonth = "April"),
        Exam(name = "Lane Sloan", resultRank = 70, birthdayMonth = "January"),
        Exam(name = "Steff Fox", resultRank = 85, birthdayMonth = "October"),
        Exam(name = "Reggie Ward", resultRank = 40, birthdayMonth = "October"),
        Exam(name = "Gail Kennedy", resultRank = 66, birthdayMonth = "May"),
        Exam(name = "Brice Mueller", resultRank = 90, birthdayMonth = "September")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.insertUser(user)
        viewModel.insertUserDetails(userDetails)
        viewModel.insertCustomers(customers)
        viewModel.insertOrder(order)
        viewModel.insertExamRank(exam)

        setContent {
            RoomRelationTheme {

                val rootNavController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()

                NavHost(rootNavController, startDestination = "home") {

                    composable("home") {
                        val getSingleRecord = viewModel.readAllData.collectAsState().value
                        val getCustomersRecord = viewModel.readAllCustomers.collectAsState().value
                        val userId = remember { mutableStateOf("") }
                        getCustomersRecord.forEach {
                            Log.e(
                                "Customers",
                                "onCreate: ${
                                    Customers(
                                        it.customerId,
                                        it.description,
                                        it.productCategory,
                                        it.quantity,
                                        it.unitPrice,
                                        it.transactionsId
                                    )
                                }",
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                TextField(value = userId.value, onValueChange = {
                                    userId.value = it
                                })

                                Spacer(modifier = Modifier.height(30.dp))

                                Button(onClick = {
                                    coroutineScope.launch {
                                        viewModel.getUserWithDetails(userId.value.toLong())
                                    }
                                }) {
                                    Text(text = "Submit")
                                }

                                Button(onClick = {
                                    rootNavController.navigate("UserDetails")
                                }) {
                                    Text(text = "UserDetails")
                                }

                                Spacer(modifier = Modifier.height(30.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(15.dp)
                                ) {
                                    Text(
                                        text = "User Id",
                                        modifier = Modifier.weight(0.3f),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Name",
                                        modifier = Modifier.weight(0.3f),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Age",
                                        modifier = Modifier.weight(0.3f),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                if (getSingleRecord.isNotEmpty()) {

                                    LazyColumn {
                                        //UserData

                                        items(getSingleRecord.size) { index ->
                                            UserDataList(getSingleRecord[index])
                                        }

                                        //Library Data
                                        item {
                                            Spacer(modifier = Modifier.height(30.dp))

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(MaterialTheme.colorScheme.primary)
                                                    .padding(15.dp)
                                            ) {
                                                Text(
                                                    text = "User Id",
                                                    modifier = Modifier.weight(0.3f),
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = "address",
                                                    modifier = Modifier.weight(0.3f),
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                        items(getSingleRecord[0].userDetails.size) { index ->
                                            UserDetailsDataList(getSingleRecord[0].userDetails[index])
                                        }
                                    }
                                }
                            }


                        }
                    }


                }

            }
        }
    }

    @Composable
    fun UserDataList(user: UserWithDetailModel) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error)
                .padding(15.dp)
        ) {
            Text(
                text = user.user.userId.toString(),
                modifier = Modifier.weight(0.3f),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.user.name,
                modifier = Modifier.weight(0.3f),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.user.email.toString(),
                modifier = Modifier.weight(0.3f),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    fun UserDetailsDataList(userDetailsEntity: UserDetailsEntity) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error)
                .padding(15.dp)
        ) {
            Text(
                text = userDetailsEntity.userDetailsId.toString(),
                modifier = Modifier.weight(0.3f),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = userDetailsEntity.address,
                modifier = Modifier.weight(0.3f),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

