package semver;

import java.util.Objects;

public class SemverUtils {

    private SemverUtils() {

    }
    protected static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    protected static int comparePreReleasesForVersions(Semver v1, Semver v2, SortOrder sortOrder) {
        String[] v1PreReleaseSegments = v1.getPreRelease().split("\\.");
        String[] v2PreReleaseSegments = v2.getPreRelease().split("\\.");
        int maxSegments = Math.max(v1PreReleaseSegments.length, v2PreReleaseSegments.length);
        for (int i = 0; i < maxSegments; i++) {
            String v1PreReleaseSegment = "";
            String v2PreReleaseSegment = "";
            if (i < v1PreReleaseSegments.length) {
                v1PreReleaseSegment = v1PreReleaseSegments[i];
            }
            if (i < v2PreReleaseSegments.length) {
                v2PreReleaseSegment = v2PreReleaseSegments[i];
            }
            int orderResult;
            if (Objects.requireNonNull(sortOrder) == SortOrder.DSC) {
                orderResult = comparePreReleaseSegmentDescending(v1PreReleaseSegment, v2PreReleaseSegment);
            } else {
                orderResult = comparePreReleaseSegmentAscending(v1PreReleaseSegment, v2PreReleaseSegment);
            }
            if (orderResult != 0) {
                return orderResult;
            }
        }
        return 0;
    }

    private static int comparePreReleaseSegmentAscending(String v1Segment, String v2Segment) {
        if (v1Segment.equals(v2Segment)) {
            return 0;
        }
        if (v1Segment.equals("")) {
            return -1;
        }
        if (v2Segment.equals("")) {
            return 1;
        }

        //If both segments are strings compare lexicographically
        if (!isNumeric(v1Segment) && !isNumeric(v2Segment)) {
            return v1Segment.compareToIgnoreCase(v2Segment);
        }
        else if (isNumeric(v1Segment) && !isNumeric(v2Segment)) { //If v1 segment is numeric and v2 segment is a string
            return -1;
        } else if (!isNumeric(v1Segment) && isNumeric(v2Segment)) { //If v1 segment is a string and v2 segment is numeric
            return 1;
        }
        else { //If both segments are numbers
            if (Integer.parseInt(v1Segment) > Integer.parseInt(v2Segment)) {
                return 1;
            }
            return -1;
        }
    }

    private static int comparePreReleaseSegmentDescending(String v1Segment, String v2Segment) {
        if (v1Segment.equals(v2Segment)) {
            return 0;
        }
        if (v1Segment.equals("")) {
            return 1;
        }
        if (v2Segment.equals("")) {
            return -1;
        }

        //If both segments are strings compare lexicographically
        if (!isNumeric(v1Segment) && !isNumeric(v2Segment)) {
            return v2Segment.compareToIgnoreCase(v1Segment);
        }
        else if (isNumeric(v1Segment) && !isNumeric(v2Segment)) { //If v1 segment is numeric and v2 segment is a string
            return 1;
        } else if (!isNumeric(v1Segment) && isNumeric(v2Segment)) { //If v1 segment is a string and v2 segment is numeric
            return -1;
        }
        else { //If both segments are numbers
            if (Integer.parseInt(v1Segment) > Integer.parseInt(v2Segment)) {
                return -1;
            }
            return 1;
        }
    }
}
