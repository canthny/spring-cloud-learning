#XmlBeanFactory
	super()->AbstractAutowireCapableBeanFactory();
	//ignoreDependencyInterface(BeanNameAware、BeanFactoryAware、BeanClassLoaderAware)

	this.XmlBeanDefinitionReader.loadBeanDefinitions(resource);

	XmlBeanDefinitionReader#doLoadBeanDefinitions(InputSource inputSource, Resource resource)throws BeanDefinitionStoreException {
		try {
			Document doc = doLoadDocument(inputSource, resource);
			return registerBeanDefinitions(doc, resource);
		}
		...
	}

	DefaultBeanDefinitionDocumentReader#registerBeanDefinitions(Document doc, XmlReaderContext readerContext) {
		this.readerContext = readerContext;
		logger.debug("Loading bean definitions");
		Element root = doc.getDocumentElement();
		doRegisterBeanDefinitions(root);
	}

	doRegisterBeanDefinitions->parseDefaultElement->processBeanDefinition->
	BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry());