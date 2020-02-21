/**
 * Copyright (C) 2016-2020 Carl-Eric Menzel <cmenzel@wicketbuch.de>
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

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class PageMounter
{
	private final WebApplication application;
	private final ScanResult scanResult;

	public PageMounter(String packagePrefix, WebApplication application)
	{
		scanResult = new ClassGraph().verbose().whitelistPackages(packagePrefix).scan();
		this.application = application;
	}

	public <T extends WebPage> void mountAllPagesExtending(Class<T> superClass)
	{
		mountAllPagesExtending("", superClass);
	}

	public <T extends WebPage> void mountAllPages()
	{
		mountAllPages("");
	}

	public <T extends WebPage> void mountAllPages(String prefix)
	{
		mountAllPagesExtending(prefix, WebPage.class);
	}

	public <T extends WebPage> void mountAllPagesExtending(String prefix, Class<T> superClass)
	{
		final ClassInfo superClassInfo = scanResult.getClassInfo(superClass.getCanonicalName());
		final ClassInfoList classes = scanResult.getSubclasses(superClass.getCanonicalName());
		classes.add(superClassInfo);
		for (ClassInfo classInfo : classes)
		{
			if (!classInfo.isAbstract())
			{
				final String canonicalName = classInfo.getName();
				if (canonicalName != null)
				{
					Class<? extends WebPage> pageClass = classInfo.loadClass(WebPage.class);
					application.mountPage(prefix + "/" + canonicalName, pageClass);
				}
			}
		}
	}
}
