package io.github.jan.kex.ui.screen.exam

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.mohamedrejeb.richeditor.model.RichTextState

sealed interface SubjectTopicValue <T> {

    val value: T

    @JvmInline
    value class RichText(override val value: RichTextState) : SubjectTopicValue<RichTextState>

    @JvmInline
    value class Markdown(override val value: MutableState<TextFieldValue>) : SubjectTopicValue<MutableState<TextFieldValue>>

}