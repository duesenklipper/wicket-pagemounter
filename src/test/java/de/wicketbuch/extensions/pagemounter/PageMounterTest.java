/**
 * Copyright (C) 2016 Carl-Eric Menzel <cmenzel@wicketbuch.de>
 * and possibly other pagemounter contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.wicketbuch.extensions.pagemounter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by calle on 16/07/16.
 */
public class PageMounterTest
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester();
	}

	@After
	public void tearDown()
	{
		tester.destroy();
		tester = null;
	}

	@Test
	public void mountAllPages() throws Exception
	{
		final WebApplication application = tester.getApplication();
		final PageMounter mounter = new PageMounter("de.wicketbuch", application);
		mounter.mountAllPages();
		assertThat(RequestCycle.get().urlFor(TestPage1.class, null).toString(), equalTo("./de.wicketbuch.extensions.pagemounter.TestPage1"));
		assertThat(RequestCycle.get().urlFor(TestPage2.class, null).toString(), equalTo("./de.wicketbuch.extensions.pagemounter.TestPage2"));
		assertThat(RequestCycle.get().urlFor(SubPage1.class, null).toString(), equalTo("./de.wicketbuch.extensions.pagemounter.SubPage1"));
		assertThat("AbstractPage is abstract, should not be mounted", RequestCycle.get().urlFor(AbstractPage.class, null).toString(), containsString("/wicket/"));
	}
	@Test
	public void mountAllPagesWithPrefix() throws Exception
	{
		final WebApplication application = tester.getApplication();
		final PageMounter mounter = new PageMounter("de.wicketbuch", application);
		mounter.mountAllPages("foo");
		assertThat(RequestCycle.get().urlFor(TestPage1.class, null).toString(), equalTo("./foo/de.wicketbuch.extensions.pagemounter.TestPage1"));
		assertThat(RequestCycle.get().urlFor(TestPage2.class, null).toString(), equalTo("./foo/de.wicketbuch.extensions.pagemounter.TestPage2"));
		assertThat(RequestCycle.get().urlFor(SubPage1.class, null).toString(), equalTo("./foo/de.wicketbuch.extensions.pagemounter.SubPage1"));
		assertThat("AbstractPage is abstract, should not be mounted", RequestCycle.get().urlFor(AbstractPage.class, null).toString(), containsString("/wicket/"));
	}

	@Test
	public void mountAllPagesExtending() throws Exception
	{
		final WebApplication application = tester.getApplication();
		final PageMounter mounter = new PageMounter("de.wicketbuch", application);
		mounter.mountAllPagesExtending(TestPage1.class);
		assertThat(RequestCycle.get().urlFor(TestPage1.class, null).toString(), equalTo("./de.wicketbuch.extensions.pagemounter.TestPage1"));
		assertThat("TestPage2 does not extend TestPage1, should not be mounted", RequestCycle.get().urlFor
				(TestPage2.class, null).toString(), containsString("/wicket/"));
		assertThat(RequestCycle.get().urlFor(SubPage1.class, null).toString(), equalTo("./de.wicketbuch.extensions.pagemounter.SubPage1"));
		assertThat("AbstractPage is abstract, should not be mounted", RequestCycle.get().urlFor(AbstractPage.class, null).toString(), containsString("/wicket/"));
	}
	@Test
	public void mountAllPagesExtendingWithPrefix() throws Exception
	{
		final WebApplication application = tester.getApplication();
		final PageMounter mounter = new PageMounter("de.wicketbuch", application);
		mounter.mountAllPagesExtending("foo", TestPage1.class);
		assertThat(RequestCycle.get().urlFor(TestPage1.class, null).toString(), equalTo("./foo/de.wicketbuch.extensions.pagemounter.TestPage1"));
		assertThat("TestPage2 does not extend TestPage1, should not be mounted", RequestCycle.get().urlFor
				(TestPage2.class, null).toString(), containsString("/wicket/"));
		assertThat(RequestCycle.get().urlFor(SubPage1.class, null).toString(), equalTo("./foo/de.wicketbuch.extensions.pagemounter.SubPage1"));
		assertThat("AbstractPage is abstract, should not be mounted", RequestCycle.get().urlFor(AbstractPage.class, null).toString(), containsString("/wicket/"));
	}
}
