/*
Copyright (c) 2017 VMware, Inc. All Rights Reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.vmware.weathervane.auction.data.imageStore;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.vmware.weathervane.auction.data.imageStore.model.ImageInfo;
import com.vmware.weathervane.auction.data.repository.image.ImageInfoRepository;

/**
 * 
 * @author Hal
 *
 */
public class ImageInfoCacheFacade {

	private static final Logger logger = LoggerFactory.getLogger(ImageInfoCacheFacade.class);
	
	private static long imageInfoMisses = 0;
	
	@Inject
	protected ImageInfoRepository imageInfoRepository;
	

	@Cacheable(value="imageInfoCache")
	public List<ImageInfo> getImageInfos(String entityType, Long entityId) {
		setImageInfoMisses(getImageInfoMisses() + 1);
		logger.info("getImageInfos entityType = " + entityType + ", entityId = " + entityId);
		return imageInfoRepository.findByKeyEntitytypeAndKeyEntityid(entityType, entityId);
	}


	public static long getImageInfoMisses() {
		return imageInfoMisses;
	}


	public static void setImageInfoMisses(long imageInfoMisses) {
		ImageInfoCacheFacade.imageInfoMisses = imageInfoMisses;
	}


}
