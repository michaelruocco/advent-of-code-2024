package uk.co.mruoc.day9;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.co.mruoc.file.FileLoader;

public class DiskMapTest {

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,0.1.2.3.4.5.6.7.8.9.10",
        "121111111111111111111,0..1.2.3.4.5.6.7.8.9.10",
        "131111111111111111111,0...1.2.3.4.5.6.7.8.9.10",
        "15131313131111111111111,0.....1...2...3...4...5.6.7.8.9.10.11",
        "2333133121414131402,00...111...2...333.44.5555.6666.777.888899",
        "90909,000000000111111111222222222"
    })
    void shouldExpandDenseInput(String input, String expectedState) {
        DiskMap map = new DiskMap(input);

        String state = map.getState();

        assertThat(state).isEqualTo(expectedState);
    }

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,010192837465..........",
        "121111111111111111111,010918273645...........",
        "131111111111111111111,010981726354............",
        "15131313131111111111111,01110987165423.......................",
        "2333133121414131402,0099811188827773336446555566..............",
        "90909,000000000111111111222222222"
    })
    void shouldCompactCorrectly(String input, String expectedState) {
        DiskMap map = new DiskMap(input);

        DiskMap compacted = map.compact(new Part1Compactor());

        assertThat(compacted.getState()).isEqualTo(expectedState);
    }

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,290",
        "121111111111111111111,270",
        "131111111111111111111,254",
        "15131313131111111111111,302",
        "2333133121414131402,1928",
        "90909,513"
    })
    void shouldCalculateChecksumCorrectly(String input, String expectedChecksum) {
        DiskMap map = new DiskMap(input);

        long checksum = map.compact(new Part1Compactor()).checksum();

        assertThat(checksum).isEqualTo(Long.parseLong(expectedChecksum));
    }

    @Test
    void shouldCalculatePuzzleChecksum() {
        String input = FileLoader.loadContentFromClasspath("day-9/disk-map.txt");
        DiskMap map = new DiskMap(input);

        DiskMap compacted = map.compact(new Part1Compactor());

        assertThat(compacted.checksum()).isEqualTo(6398608069280L);
    }
}
