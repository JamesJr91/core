/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.common.assets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import net.rpgtoolkit.common.CorruptAssetException;

/**
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public class AssetManager {
    
    public AssetManager() {
        this.resolvers = new ArrayList<>();
        this.serializers = new TreeSet<>(
                new AssetSerializer.PriorityComparator());
        this.assets = new HashMap<>();
    }
    
    /***
     * Returns the number of assets managed.
     * @return count of assets managed
     */
    public int getAssetCount() {
        return this.assets.size();
    }
    
    public void registerSerializer(final AssetSerializer serializer) {
        if (serializer == null) {
            throw new NullPointerException();
        }
        this.serializers.add(serializer);
    }
    
    public void registerResolver(final AssetHandleResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException();
        }
        this.resolvers.add(resolver);
    }
    
    public AssetHandle deserialize(AssetDescriptor descriptor) 
        throws IOException, CorruptAssetException {
        
        if (assets.containsKey(descriptor)) {
            return assets.get(descriptor);
        }
        
        final AssetHandle handle = resolve(descriptor);
        
        if (handle != null) {
            for (AssetSerializer serializer : serializers) {
                if (serializer.canDeserialize(descriptor)) {
                    serializer.deserialize(handle);
                    if (handle.getAsset() != null) {
                        assets.put(descriptor, handle);
                    }
                }
            }
        }
        
        return handle;
        
    }
    
    private AssetHandle resolve(final AssetDescriptor descriptor) {
        
        for (final AssetHandleResolver resolver : resolvers) {
            if (resolver.canResolve(descriptor)) {
                return resolver.resolve(descriptor);
            }
        }
        
        return null;
        
    }
    
    private final TreeSet<AssetSerializer> serializers;
    private final List<AssetHandleResolver> resolvers;
    private final Map<AssetDescriptor, AssetHandle> assets;
    
}
