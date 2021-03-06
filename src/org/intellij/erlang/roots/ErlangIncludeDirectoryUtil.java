/*
 * Copyright 2012-2014 Sergey Ignatov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.erlang.roots;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.erlang.jps.model.ErlangIncludeSourceRootType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ErlangIncludeDirectoryUtil {
  private ErlangIncludeDirectoryUtil() {
  }

  @NotNull
  public static List<VirtualFile> getIncludeDirectories(@Nullable Module module) {
    if (module == null) return ContainerUtil.emptyList();
    ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
    return rootManager.getSourceRoots(ErlangIncludeSourceRootType.INSTANCE);
  }

  public static void markAsIncludeDirectory(@NotNull ContentEntry contentEntry, @NotNull VirtualFile directory) {
    contentEntry.addSourceFolder(directory, ErlangIncludeSourceRootType.INSTANCE);
  }

  public static void markAsIncludeDirectory(@NotNull Module module, @NotNull VirtualFile directory) {
    ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
    ModifiableRootModel rootModel = rootManager.getModifiableModel();
    for (ContentEntry contentEntry : rootModel.getContentEntries()) {
      VirtualFile contentRootDirectory = contentEntry.getFile();
      if (contentRootDirectory != null && VfsUtilCore.isAncestor(contentRootDirectory, directory, false)) {
        markAsIncludeDirectory(contentEntry, directory);
        rootModel.commit();
        return;
      }
    }
    rootModel.dispose();
  }
}
