import { defineConfig } from 'astro/config';
import react from '@astrojs/react';
import tailwindcss from '@tailwindcss/vite';
import mdx from '@astrojs/mdx';
import sitemap from '@astrojs/sitemap';
import icon from 'astro-icon';
import netlify from '@astrojs/netlify';

export default defineConfig({
  site: 'https://astroship.web3templates.com',

  output: 'server', // SSR

  integrations: [mdx(), sitemap(), icon(), react()],

  vite: {
    plugins: [tailwindcss()],
  },

  adapter: netlify(),
});
