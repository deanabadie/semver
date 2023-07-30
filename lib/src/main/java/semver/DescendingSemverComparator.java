package semver;

import java.util.Comparator;

import static semver.SemverUtils.comparePreReleasesForVersions;

class DescendingSemverComparator implements Comparator<Semver> {

    @Override
    public int compare(Semver v1, Semver v2) {
        if (v1.getMajor() != v2.getMajor()) {
            return v2.getMajor() - v1.getMajor();
        } else if (v2.getMinor() != v1.getMinor()) {
            return v2.getMinor() - v1.getMinor();
        } else if (v2.getPatch() != v1.getPatch()) {
            return v2.getPatch() - v1.getPatch();
        } else if (!v1.getPreRelease().equals(v2.getPreRelease())) {
            if (v1.getPreRelease() == null) {
                return -1;
            }
            if (v2.getPreRelease() == null) {
                return 1;
            }
            return comparePreReleasesForVersions(v1, v2, SortOrder.DSC);
        } else {
            return 0;
        }
    }
}
