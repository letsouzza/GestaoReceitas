package br.senai.sp.jandira.foodrecipe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.foodrecipe.R

val poppinsFamily = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold)
)
val antonscFamily = FontFamily(
    Font(R.font.antonsc_regular, FontWeight.Normal)
)
val gorditasFamily = FontFamily(
    Font(R.font.gorditas_regular, FontWeight.Normal)
)
val podkovaFamily = FontFamily(
    Font(R.font.podkova_bold, FontWeight.Bold),
    Font(R.font.podkova_medium, FontWeight.Medium),
    Font(R.font.podkova_regular, FontWeight.Normal),
    Font(R.font.podkova_semibold, FontWeight.SemiBold),
    Font(R.font.podkova_extrabold, FontWeight.ExtraBold)
)
val rethinkFamily = FontFamily(
    Font(R.font.rethinksans_bold, FontWeight.Bold),
    Font(R.font.rethinksans_medium, FontWeight.Medium),
    Font(R.font.rethinksans_regular, FontWeight.Normal),
    Font(R.font.rethinksans_semibold, FontWeight.SemiBold),
    Font(R.font.rethinksans_extrabold, FontWeight.ExtraBold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)