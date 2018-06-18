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
package com.vmware.weathervane.auction.data.dao;

import java.util.Date;
import java.util.List;

import com.vmware.weathervane.auction.data.model.Auction;
import com.vmware.weathervane.auction.data.model.Item;

public interface AuctionDao extends GenericDao<Auction, Long> {

	Long getCountWithState(Auction.AuctionState state);

	Long getItemCountforAuction(Auction theAuction);
	List<Item> getItemPageForAuction(Auction theAuction, int page, int pageSize);

	List<Auction> getAuctionsToStart(Date endTime);
	List<Item> getItemsForAuction(Long auctionId);
	Item getFirstItem(Auction theAuction);
	Item getNextItem(Auction theAuction, Long itemId);
	List<Auction> getActiveAuctions();
	List<Auction> getAuctionsPage(int page, int pageSize, Auction.AuctionState state);

	Auction addAuctionForAuctioneer(Auction anAuction, Long userId);

	/*
	 * These methods are all used by the benchmark infrastructure, and not
	 * by the Auction application
	 */
	Long countByCurrent(Boolean current);
	Long countByCurrentAndActivated(Boolean current, Boolean activated);
	
	List<Auction> findByCurrent(Boolean current, int numDesired);
	List<Auction> findByCurrentAndActivated(Boolean current, Boolean activated);
	
	/**
	 * This method resets the auction to a state that is correct for an 
	 * auction that will be run in the future.  It is used by the DBPrep
	 * program to reset auctions that were preloaded and then were used
	 * in a previous run.
	 * 
	 * @param auction
	 */
	void resetToFuture(Auction auction);
		
	/**
	 * This method prepares an auction to be used in the next benchmark run by 
	 * setting its startTime to the time at which all runs start. It is assumed
	 * that the auction is in the non-activated state generated by the DBLoader
	 * and reset by DBPrep
	 * 
	 * @param auction
	 */
	void setToActivated(Auction auction);

	Item getNextUnsoldItem(Auction theAuction);
	
}
