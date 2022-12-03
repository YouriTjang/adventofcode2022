import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GameTest {

    @Test
    fun resultPoints() {
        assertThat(Game("B", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("C", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("A", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("X", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("B", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("B", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("B", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("B", "X").resultPoints()).isEqualTo(1)
        assertThat(Game("B", "X").resultPoints()).isEqualTo(1)
    }
}