package semver;

class Semver {

    private int major;
    private int minor;
    private int patch;
    private String preRelease;
    private String buildMetadata;

    protected Semver(String version) {
        if (SemverTools.isValidSemver(version)) {
            String[] tokens = version.split("\\.", 3);
            if (tokens[2].contains("-") || tokens[2].contains("+")) {
                String[] versionSuffix = tokens[2].split("\\+", 2);
                if (versionSuffix.length > 1) {
                    this.buildMetadata = versionSuffix[1];
                    tokens[2] = versionSuffix[0];
                }
                versionSuffix = tokens[2].split("-", 2);
                if (versionSuffix.length > 1) {
                    this.preRelease = versionSuffix[1];
                    tokens[2] = versionSuffix[0];
                }
            }
            this.patch = Integer.parseInt(tokens[2]);
            this.minor = Integer.parseInt(tokens[1]);
            this.major = Integer.parseInt(tokens[0]);
        }
        else {
            throw new IllegalVersionException("Version does not follow semver convention");
        }
    }

    protected int getMajor() {
        return major;
    }

    protected void setMajor(Integer major) {
        this.major = major;
    }

    protected int getMinor() {
        return minor;
    }

    protected void setMinor(Integer minor) {
        this.minor = minor;
    }

    protected int getPatch() {
        return patch;
    }

    protected void setPatch(Integer patch) {
        this.patch = patch;
    }

    protected String getPreRelease() {
        return preRelease;
    }

    protected void setPreRelease(String preRelease) {
        this.preRelease = preRelease;
    }

    protected String getBuildMetadata() {
        return buildMetadata;
    }

    protected void setBuildMetadata(String buildMetadata) {
        this.buildMetadata = buildMetadata;
    }

    @Override
    public String toString() {
        StringBuilder semverStringBuilder = new StringBuilder().append(major).append(".")
                .append(minor).append(".")
                .append(patch);
        if (this.preRelease != null) {
            semverStringBuilder.append("-").append(preRelease);
        }
        if (this.buildMetadata != null) {
            semverStringBuilder.append("+").append(buildMetadata);
        }
        return semverStringBuilder.toString();
    }
}
