#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "proctab process manager"
HOMEPAGE = "https://troydhanson.github.com/proctab"
SECTION = "base"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=014148094f8c384ce40dac7900b965c4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "1880e9a893ed8615ca32676cc5851736ad42b30c"
SRC_URI = "git://github.com/troydhanson/proctab.git;branch=2016.0"

S = "${WORKDIR}/git"

inherit autotools systemd

# See the Yocto mega manuel section 26.134 systemd.bbclass.
# We install the systemd unit file in ${D}${systemd_unitdir}/system
# We set SYSTEMD_SERVICE_${PN} to the service file basename.
# FIXME - do substitutions with setup-initscript and dump output file
# FIXME  to proctab.service. Let SYSTEMD_SERVICE_${PN} install it.
# FIXME - else, fix or confirm that ${base_bindir} is what configure ran with
do_install_append() {
  ${S}/initscripts/setup-initscript --install-service --initsys systemd --bindir ${base_bindir} --systemd-unit-dir "${D}/${systemd_unitdir}/system"
  SYSTEMD_SERVICE_${PN} = "proctab.service"
}

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""

