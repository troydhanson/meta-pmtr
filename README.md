meta-pmtr
=========

Yocto/OpenEmbedded layer for [pmtr](https://troydhanson.github.com/pmtr).

Usage:

* clone this repo
* add it to conf/bblayers.conf
* add pmtr recipe to an image. E.g., add this to conf/local.conf: `IMAGE_INSTALL_append = " pmtr"`
* build an image e.g. `bitbake core-image-minimal`

The resulting image has the executable `/usr/bin/pmtr` and the initial,
empty configuration file `/etc/pmtr.conf`. More precisely they are placed in
`${bindir}/pmtr` and `${sysconfdir}/pmtr.conf`. Additionally it has a 
sysvinit script installed that starts pmtr at boot.

