package dev.byandrev.habito.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.byandrev.habito.ui.theme.HabitoTheme

@Composable @Stable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    buttonColor: Color,
    onTap: () -> Unit = {},
) {
    Button(
        modifier = modifier.height(HabitoTheme.dimens.buttonHeightNormal),
        shape = RoundedCornerShape(HabitoTheme.dimens.roundedShapeNormal),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        onClick = { onTap() },
    ) {
        Text(
            text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.bodyLarge,
            color = textColor
        )
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CustomButtonPreview() {
    HabitoTheme {
        CustomButton(
            text = "Start",
            textColor = MaterialTheme.colorScheme.surface,
            buttonColor = MaterialTheme.colorScheme.primary
        )
    }
}