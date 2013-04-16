	def searchInList(list,packageName){
		for (keyword in list) {
			println "list:$list,  packageName:$packageName,  keyword:$keyword "
			if (packageName?.toLowerCase()?.contains(keyword?.toLowerCase())) {
				println true
				return true
			}
		}
		println false
		return false
	}

	def deleteAll(){
		log.info "deleting..."
		def deleteFlag= true
		def dlist = grailsApplication.getArtefacts("Domain")
		def filteredList= dlist
		def includeList= []
		def excludeList= ['_DemoPage']

		def dd = filteredList.name.clone()
		if (includeList){
			dd= includeList.intersect(dd)
		}
		dd = dd - excludeList

		def i= 0
		def z= 0
		while( dd.size() > 0  || ++z<=100){
			println "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"
			println "this"
			println "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"
			println dd.size()
			
			for(int j=0;j<dd.size();j++) {
				def it = dd[j]
				def f = deleteIt(it,filteredList,deleteFlag )
				if (!f) {
				}
				else {
					def ii=dd.indexOf(it)
					if (ii>=0) {
						if (deleteFlag) 
						log.debug "deleting: "+dd.remove(ii)
						else 
						log.debug "displaying: "+dd.remove(ii)
						i++
					}
				}
			}
		}
		if (deleteFlag) 
			log.debug "total deleted $i"
		else
			log.debug "total displayed $i"

		redirect (controller:'home' )
	}

	def deleteIt(item,filteredList,doDelete){
		try{
			def myclass =  filteredList.find{it.name==item}
			if(myclass.clazz.list()) println "${item}         "+myclass.clazz.list()
			if(doDelete) {
				println item
				myclass.clazz.executeUpdate("delete ${item}         c where 1=1")
			}
			return true
		}
		catch(Exception e){
			//println e ignoring
			return false
		}
	}

