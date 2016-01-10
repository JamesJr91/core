/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.common.assets.serialization.legacy;

import java.io.IOException;
import java.nio.ByteOrder;

import net.rpgtoolkit.common.assets.AbstractAssetSerializer;
import net.rpgtoolkit.common.assets.AssetDescriptor;
import net.rpgtoolkit.common.assets.AssetException;
import net.rpgtoolkit.common.assets.AssetHandle;
import net.rpgtoolkit.common.assets.Item;
import net.rpgtoolkit.common.io.Paths;

/**
 * Serializes item assets
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public class LegacyItemSerializer
  extends AbstractAssetSerializer {

  private final String HEADER_MAGIC = "RPGTLKIT ITEM";
  private final int HEADER_VERSION_MAJOR = 2;
  private final int HEADER_VERSION_MINOR = 7;

  @Override
  public int priority() {
    return 1; // not our first choice
  }

  @Override
  public boolean serializable(AssetDescriptor descriptor) {
    final String ext = Paths.extension(descriptor.getURI().getPath());
    return (ext.endsWith(".itm"));
  }

  @Override
  public boolean deserializable(AssetDescriptor descriptor) {
    return serializable(descriptor);
  }

  @Override
  public void serialize(AssetHandle handle)
    throws IOException, AssetException {
    throw new UnsupportedOperationException("unimplemented");
  }

  @Override
  public void deserialize(AssetHandle handle)
    throws IOException, AssetException {
    throw new UnsupportedOperationException("unimplemented");
  }

}
