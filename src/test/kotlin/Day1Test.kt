import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `getMaxElf`() {
        assertThat(Day1().getMaxElf(testData)).isEqualTo(24000)
    }

    @Test
    fun getSum3MaxElfs(){
        assertThat(Day1().getMaxElfs(testData, 3)).isEqualTo(45000)
    }

    val testData = """
            1000
            2000
            3000
            
            4000
            
            5000
            6000
            
            7000
            8000
            9000
            
            10000
        """
}