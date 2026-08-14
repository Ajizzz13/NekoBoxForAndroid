#!/bin/bash
sed -i 's/getCurrentGroupFragment()?.adapter?.filter(query)/getCurrentGroupFragment()?.filter(query)/g' app/src/main/java/io/nekohasekai/sagernet/ui/ConfigurationFragment.kt
sed -i 's/fragment.adapter!!.configurationIdList.indexOf(selectedProxy)/fragment.indexOf(selectedProxy)/g' app/src/main/java/io/nekohasekai/sagernet/ui/ConfigurationFragment.kt
