package org.sherman.benchmark.java;

import com.google.common.base.Preconditions;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorShuffle;
import jdk.incubator.vector.VectorSpecies;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.sherman.interview.vector.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 Benchmark                                       Mode  Cnt  Score   Error   Units
 IntersectionBenchmark.hasIntersectionScalar    thrpt   10  2.591 ± 1.167  ops/us
 IntersectionBenchmark.hasIntersectionVector    thrpt   10  4.698 ± 0.641  ops/us
 IntersectionBenchmark.hasNoIntersectionScalar  thrpt   10  1.726 ± 0.266  ops/us
 IntersectionBenchmark.hasNoIntersectionVector  thrpt   10  2.319 ± 0.211  ops/us
 */
@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class IntersectionBenchmark {
    private final Logger logger = LoggerFactory.getLogger(IntersectionBenchmark.class);
    private int[] data1;
    private int[] data2;
    private int[] data3;

    @Setup(Level.Trial)
    public void generateData() {
        // no intersection, size = 256
        data1 = new int[]{0, 5, 6, 17, 18, 27, 32, 43, 51, 51, 58, 74, 76, 82, 85, 85, 93, 108, 122, 133, 134, 141, 157, 158, 159, 166, 176, 189, 198, 205, 208, 220, 233, 255, 284, 298, 300, 303, 317, 321, 323, 328, 341, 352, 355, 358, 370, 391, 392, 395, 403, 411, 418, 421, 422, 426, 433, 435, 436, 459, 459, 462, 464, 480, 487, 495, 513, 522, 537, 554, 555, 564, 574, 587, 588, 595, 597, 601, 607, 625, 628, 640, 653, 657, 663, 666, 673, 695, 696, 706, 708, 712, 718, 734, 749, 756, 761, 766, 775, 777, 778, 784, 785, 801, 808, 812, 831, 837, 842, 843, 844, 857, 871, 878, 890, 900, 905, 921, 925, 925, 950, 952, 976, 978, 982, 983, 989, 1015, 1020, 1022, 1028, 1029, 1039, 1054, 1060, 1061, 1072, 1074, 1077, 1078, 1089, 1101, 1105, 1122, 1134, 1135, 1142, 1144, 1144, 1170, 1173, 1175, 1180, 1185, 1192, 1242, 1249, 1264, 1282, 1295, 1299, 1301, 1306, 1315, 1323, 1326, 1329, 1333, 1345, 1347, 1352, 1361, 1363, 1365, 1381, 1394, 1395, 1420, 1420, 1435, 1450, 1463, 1467, 1476, 1480, 1497, 1502, 1503, 1503, 1508, 1523, 1530, 1535, 1541, 1545, 1548, 1550, 1553, 1557, 1576, 1576, 1579, 1582, 1593, 1595, 1610, 1619, 1619, 1620, 1627, 1635, 1645, 1647, 1655, 1662, 1674, 1687, 1687, 1694, 1702, 1706, 1721, 1727, 1727, 1737, 1744, 1744, 1746, 1752, 1764, 1768, 1785, 1790, 1791, 1803, 1812, 1833, 1838, 1851, 1852, 1856, 1856, 1863, 1867, 1869, 1870, 1898, 1904, 1911, 1925, 1929, 1950, 2007, 2009, 2019, 2033};
        data2 = new int[]{8, 31, 38, 38, 39, 42, 66, 69, 73, 75, 87, 88, 105, 116, 120, 123, 124, 129, 146, 153, 156, 170, 172, 177, 199, 234, 237, 238, 251, 259, 260, 261, 269, 275, 277, 278, 282, 286, 296, 307, 308, 315, 335, 337, 349, 360, 368, 369, 377, 378, 402, 409, 416, 424, 425, 428, 429, 438, 439, 440, 443, 445, 446, 450, 465, 471, 482, 483, 485, 486, 496, 496, 497, 497, 500, 501, 504, 506, 506, 508, 520, 561, 575, 576, 577, 637, 642, 656, 693, 738, 740, 742, 750, 754, 757, 780, 783, 807, 809, 815, 817, 840, 851, 865, 869, 882, 904, 909, 911, 919, 929, 930, 937, 949, 951, 955, 960, 974, 981, 985, 990, 995, 997, 1001, 1025, 1033, 1038, 1046, 1046, 1048, 1055, 1062, 1081, 1081, 1083, 1083, 1091, 1094, 1103, 1109, 1110, 1111, 1115, 1118, 1141, 1163, 1178, 1182, 1189, 1191, 1194, 1199, 1220, 1222, 1231, 1237, 1238, 1254, 1274, 1276, 1279, 1284, 1303, 1360, 1375, 1382, 1400, 1405, 1406, 1419, 1421, 1422, 1431, 1449, 1452, 1468, 1470, 1486, 1496, 1515, 1516, 1527, 1529, 1540, 1544, 1577, 1577, 1592, 1599, 1611, 1611, 1615, 1616, 1634, 1650, 1651, 1672, 1675, 1678, 1684, 1684, 1685, 1693, 1693, 1696, 1704, 1707, 1716, 1718, 1733, 1750, 1758, 1775, 1777, 1779, 1787, 1801, 1801, 1816, 1820, 1837, 1843, 1854, 1861, 1864, 1864, 1866, 1879, 1880, 1889, 1896, 1897, 1902, 1916, 1922, 1940, 1945, 1955, 1956, 1958, 1970, 1975, 1981, 1990, 2004, 2010, 2015, 2021, 2025, 2029, 2031, 2032, 2034, 2038, 2038, 2039};
        // has intersection in the middle
        data3 = new int[]{8, 31, 38, 38, 39, 42, 66, 69, 73, 75, 87, 88, 105, 116, 120, 123, 124, 129, 146, 153, 156, 170, 172, 177, 199, 234, 237, 238, 251, 259, 260, 261, 269, 275, 277, 278, 282, 286, 296, 307, 308, 315, 335, 337, 349, 360, 368, 369, 377, 378, 402, 409, 416, 424, 425, 428, 429, 438, 439, 440, 443, 445, 446, 450, 465, 471, 482, 483, 485, 486, 496, 496, 497, 497, 500, 501, 504, 506, 506, 508, 520, 561, 575, 576, 577, 637, 642, 656, 693, 738, 740, 742, 750, 754, 757, 780, 783, 807, 809, 815, 817, 840, 851, 865, 869, 882, 904, 909, 911, 919, 929, 930, 937, 949, 951, 955, 960, 974, 981, 985, 990, 995, 997, 1015, 1025, 1033, 1038, 1046, 1046, 1048, 1055, 1062, 1081, 1081, 1083, 1083, 1091, 1094, 1103, 1109, 1110, 1111, 1115, 1118, 1141, 1163, 1178, 1182, 1189, 1191, 1194, 1199, 1220, 1222, 1231, 1237, 1238, 1254, 1274, 1276, 1279, 1284, 1303, 1360, 1375, 1382, 1400, 1405, 1406, 1419, 1421, 1422, 1431, 1449, 1452, 1468, 1470, 1486, 1496, 1515, 1516, 1527, 1529, 1540, 1544, 1577, 1577, 1592, 1599, 1611, 1611, 1615, 1616, 1634, 1650, 1651, 1672, 1675, 1678, 1684, 1684, 1685, 1693, 1693, 1696, 1704, 1707, 1716, 1718, 1733, 1750, 1758, 1775, 1777, 1779, 1787, 1801, 1801, 1816, 1820, 1837, 1843, 1854, 1861, 1864, 1864, 1866, 1879, 1880, 1889, 1896, 1897, 1902, 1916, 1922, 1940, 1945, 1955, 1956, 1958, 1970, 1975, 1981, 1990, 2004, 2010, 2015, 2021, 2025, 2029, 2031, 2032, 2034, 2038, 2038, 2039};

        Arrays.sort(data1);
        Arrays.sort(data2);

        Preconditions.checkArgument(!ArrayUtils.hasIntersectionScalar(data1, data2), "No intersection is expected!");
        Preconditions.checkArgument(ArrayUtils.hasIntersectionScalar(data1, data3), "Intersection is expected!");
    }

    @Benchmark
    public void hasNoIntersectionVector(Blackhole blackhole) {
        var result = ArrayUtils.hasIntersectionVector(data1, data2);
        blackhole.consume(result);
    }

    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_128;

    @Benchmark
    public void hasNoIntersectionScalar(Blackhole blackhole) {
        var result = ArrayUtils.hasIntersectionScalar(data1, data2);
        blackhole.consume(result);
    }

    @Benchmark
    public void hasIntersectionVector(Blackhole blackhole) {
        var result = ArrayUtils.hasIntersectionVector(data1, data3);
        blackhole.consume(result);
    }

    @Benchmark
    public void hasIntersectionScalar(Blackhole blackhole) {
        var result = ArrayUtils.hasIntersectionScalar(data1, data3);
        blackhole.consume(result);
    }

    private static final VectorShuffle<Integer> shuffle = VectorShuffle.fromValues(SPECIES, 3, 0, 1, 2);

    //@Benchmark
    public void rearrange1(Blackhole blackhole) {
        var vector = IntVector.fromArray(SPECIES, data1, 0);
        blackhole.consume(vector.rearrange(shuffle));
    }

    //@Benchmark
    public void shuffle(Blackhole blackhole) {
        var vector = IntVector.fromArray(SPECIES, data1, 0);
        blackhole.consume(vector.rearrange(vector.species().iotaShuffle(0, 0, true)));
    }

    //@Benchmark
    public void shuffleFromValue(Blackhole blackhole) {
        var vector = IntVector.fromArray(SPECIES, data1, 0);
        blackhole.consume(vector.rearrange(vector.species().shuffleFromValues(3, 0, 1, 2)));
    }

    //@Benchmark
    public void slice(Blackhole blackhole) {
        var vector = IntVector.fromArray(SPECIES, data1, 0);
        blackhole.consume(vector.slice(3, vector));
    }

    //@Benchmark
    public void broadcast(Blackhole blackhole) {
        var vector = IntVector.fromArray(SPECIES, data1, 0);
        blackhole.consume(vector.broadcast(vector.lane(0)));
    }

    // reading from array
    //@Benchmark
    public void read(Blackhole blackhole) {
        var bound = SPECIES.loopBound(data1.length);
        int i = 0;
        int sum = 0;
        while (i < bound) {
            var vector = IntVector.fromArray(SPECIES, data1, i);
            i += SPECIES.length();
            sum += vector.length();
        }
        blackhole.consume(sum);
    }

    //@Benchmark
    public void baseline(Blackhole blackhole) {
        int i = 0;
        int sum = 0;
        while (i < data1.length) {
            var element = data1[i];
            i++;
            sum += element;
        }
        blackhole.consume(sum);
    }
}
