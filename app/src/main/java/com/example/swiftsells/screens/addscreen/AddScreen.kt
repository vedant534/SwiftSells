package com.example.swiftsells.screens.addscreen

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.swiftsells.model.ProductDetails
import com.example.swiftsells.navigation.SwiftScreens
import com.example.swiftsells.ui.theme.BgGrey
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SecondaryColor
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<AddScreenViewModel>()

    var btnCnt by remember {
        mutableIntStateOf(0)
    }
    var productType by remember {
        mutableStateOf("")
    }
    var productNameText by remember {
        mutableStateOf("")
    }
    var productPrice by remember {
        mutableStateOf("")
    }
    var productTax by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    //Dialog box shows when product is submitted
    if (viewModel.isDialogShown) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, color = Color.DarkGray, shape = RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(containerColor = BgGrey)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    Text(
                        text = "Review your product before listing",
                        style = MaterialTheme.typography.headlineSmall,
                        color = SecondaryColor
                    )
                    Divider()
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        Text("Name : $productNameText", color = Color.White)
                        Text(text = "Price : $productPrice$", color = Color.White)
                        Text(text = "Tax applicable: $productTax%", color = Color.White)
                        Text(text = "Type : $productType", color = Color.White)
                        Text(
                            text = "Image : SwiftSellsDefault.png \n ( by default image)",
                            color = Color.White
                        )
                        Row {
                            Button(
                                onClick = {
                                    viewModel.onDismissDialog()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SecondaryColor,
                                    contentColor = MainBgColor,
                                ),
                            ) {
                                Text(text = "cancel")
                            }

                            Spacer(modifier = Modifier.width(50.dp))

                            Button(
                                onClick = {
                                    val myProduct = ProductDetails(
                                        "",
                                        price = productPrice.toDouble(),
                                        productName = productNameText,
                                        productType = productType,
                                        tax = productTax.toDouble()
                                    )
                                    viewModel.pushProduct(myProduct)
                                    viewModel.onDismissDialog()
                                    navController.navigate(SwiftScreens.HomeScreen.name)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SecondaryColor,
                                    contentColor = MainBgColor,
                                )
                            ) {
                                Text(text = "confirm")
                            }
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AddTopBar(navController)
        },
        floatingActionButton = {
            Box(modifier = Modifier.padding(10.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(
                        color = BgGrey,
                        shape = RoundedCornerShape(14.dp)
                    )
                ) {
                    FloatingActionButton(
                        modifier = Modifier.size(80.dp),
                        onClick = {
                            btnCnt++
                            if (productType.isNotEmpty() &&
                                productNameText.isNotEmpty() &&
                                validateDecimals(productPrice) &&
                                validateDecimals(productTax)
                            ) {
                                viewModel.onAddClick()
                                //dialog inserts

                                //
                            } else {
                                Toast.makeText(
                                    context,
                                    "Invalid input. Please follow our guidelines to add your product.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                        containerColor = SecondaryColor,
                        contentColor = MainBgColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "FAB")
                    }
                    Text(text = "Add", color = SecondaryColor)
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MainBgColor
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp)
            ) {

                var isExpanded by remember {
                    mutableStateOf(false)
                }

                val keyboardController = LocalSoftwareKeyboardController.current
                val focusManager = LocalFocusManager.current

                val otfDefaultColors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = SecondaryColor,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                    focusedTrailingIconColor = SecondaryColor,
                    focusedLeadingIconColor = SecondaryColor
                )

                Text(
                    text = "Product Name", fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = productNameText,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {
                        productNameText = it
                    },
                    textStyle = TextStyle(textAlign = TextAlign.Start, fontSize = 15.sp),
                    placeholder = { Text(text = "Name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ),
                    colors = otfDefaultColors
                )
                Box(modifier = Modifier.align(Alignment.End)) {
                    if (productNameText.isEmpty() && btnCnt > 0) Text(
                        text = "* can't be blank",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                    else if (btnCnt > 0) Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "ok",
                        tint = SecondaryColor
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Select Product Type", fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it }) {
                    OutlinedTextField(
                        value = productType,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Start,
                            fontSize = 15.sp
                        ),
                        colors = otfDefaultColors,
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth(),
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false },
                    ) {
                        val ddFontSize = 17.sp

                        val ddItem = listOf(
                            "Clothing",
                            "Electronics",
                            "Essential",
                            "Food And Beverages",
                            "Home Furniture",
                            "OS",
                            "Out of Stock",
                            "Other",
                            "Product",
                            "Type A",
                            "Type B",
                            "Type C",
                            "World Cup"
                        )

                        ddItem.forEach {
                            DropDownItemForAll(label = it, ddFontSize) {
                                productType = it
                                isExpanded = false
                                focusManager.clearFocus()
                            }
                        }

                    }
                }
                Box(modifier = Modifier.align(Alignment.End)) {
                    if (productType.isEmpty() && btnCnt > 0) Text(
                        text = "* can't be blank",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                    else if (btnCnt > 0) Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "ok",
                        tint = SecondaryColor
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Product Price", fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = productPrice,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {
                        productPrice = it
                    },
                    textStyle = TextStyle(textAlign = TextAlign.Start, fontSize = 15.sp),
                    placeholder = { Text(text = "Price in USD (e.g., 19.99 or 300 )") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ),
                    colors = otfDefaultColors,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AttachMoney, contentDescription = null)
                    }
                )
                Box(modifier = Modifier.align(Alignment.End)) {
                    if (btnCnt > 0) {
                        if ((productPrice.isEmpty())) Text(
                            text = "* can't be blank",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                        else if (!validateDecimals(productPrice.trim())) Text(
                            text = "* Invalid input",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                        else Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "ok",
                            tint = SecondaryColor
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Product Tax", fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = productTax,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {
                        productTax = it
                    },
                    textStyle = TextStyle(textAlign = TextAlign.End, fontSize = 15.sp),
                    placeholder = { Text(text = "Tax rate (e.g., 8.25)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ),
                    colors = otfDefaultColors,
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Percent, contentDescription = null)
                    }
                )
                Box(modifier = Modifier.align(Alignment.End)) {
                    if (btnCnt > 0) {
                        if ((productTax.isEmpty())) Text(
                            text = "* can't be blank",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                        else if (!validateDecimals(productTax.trim())) Text(
                            text = "* Invalid input",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                        else Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "ok",
                            tint = SecondaryColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Product Image", fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))


                // image picking starts
                ImagePicker()
            }
        }
    }
}


@Composable
fun DropDownItemForAll(label: String, fontSize: TextUnit, onClick: () -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                textAlign = TextAlign.Start,
            )
        },
        onClick = onClick,
        colors = MenuDefaults.itemColors(textColor = Color.Black),
        modifier = Modifier.fillMaxWidth()
    )

}

fun validateDecimals(input: String): Boolean {
    val pattern = Pattern.compile("^[0-9]+(\\.[0-9]+)?$")
    val matcher = pattern.matcher(input)
    return matcher.matches()
}

@Composable
fun ImagePicker() {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        imageUri?.let {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            val originalBitmap = ImageDecoder.decodeBitmap(source)

            // Resize the bitmap to a 1:1 aspect ratio
            val resizedBitmap: Bitmap = if (originalBitmap.width > originalBitmap.height) {
                Bitmap.createScaledBitmap(
                    originalBitmap,
                    originalBitmap.height,
                    originalBitmap.height,
                    true
                )
            } else {
                Bitmap.createScaledBitmap(
                    originalBitmap,
                    originalBitmap.width,
                    originalBitmap.width,
                    true
                )
            }

            bitmap.value = resizedBitmap
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryColor,
                    contentColor = MainBgColor,
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(text = "Pick Image")
            }
            Spacer(modifier = Modifier.width(12.dp))
            if (bitmap.value == null) Text(
                text = "* No Image Set",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        bitmap.value?.let {
            Card(
                modifier = Modifier.padding(2.dp),
                border = BorderStroke(1.5.dp, SecondaryColor), shape = RectangleShape
            ) {
                Image(
                    bitmap = it.asImageBitmap(), contentDescription = null,
                    modifier = Modifier
                        .size(150.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }

}
