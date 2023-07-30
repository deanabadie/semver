package semver;

import java.util.Comparator;

import static semver.SemverUtils.comparePreReleasesForVersions;

class AscendingSemverComparator implements Comparator<Semver> {

    @Override
    public int compare(Semver v1, Semver v2) {
        if (v1.getMajor() != v2.getMajor()) {
            return v1.getMajor() - v2.getMajor();
        } else if (v1.getMinor() != v2.getMinor()) {
            return v1.getMinor() - v2.getMinor();
        } else if (v1.getPatch() != v2.getPatch()) {
            return v1.getPatch() - v2.getPatch();
        } else if (!v1.getPreRelease().equals(v2.getPreRelease())) {
            if (v1.getPreRelease() == null) {
                return 1;
            }
            if (v2.getPreRelease() == null) {
                return -1;
            }
            return comparePreReleasesForVersions(v1, v2, SortOrder.ASC);
        } else {
            return 0;
        }
    }
}
