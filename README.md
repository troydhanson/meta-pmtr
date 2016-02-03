meta-proctab
============

Yocto/OpenEmbedded layer for [https://troydhanson.github.com/proctab](proctab).

Usage:

* clone this repo
* add it to conf/bblayers.conf
* add proctab recipe to an image. E.g., add this to conf/local.conf: 

    IMAGE_INSTALL_append = " proctab"

* build an image e.g. `bitbake core-image-minimal`

The resulting image has the executable `/usr/bin/proctab` and the initial,
empty configuration file `/etc/proctab.conf`. More precisely they are placed in
`${bindir}/proctab` and `${sysconfdir}/proctab.conf`. Additionally it has a 
systemd service installed that starts proctab at boot.

systemd

The recipe is written for a systemd-based host. For building a sysvinit based
image, the recipe would need to use different arguments to `setup-initscript` in
the `do_install_append` function. (Run `setup-initscript -h` to see its options,
this can be done on the build host on a checkout of the proctab repo).

These lines in `local.conf` generate a systemd-only image:

```
# USE SYSTEMD ONLY
DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""
```
